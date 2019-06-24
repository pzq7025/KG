import jieba
import jieba.posseg as pesg

jieba.load_userdict(r".\load_file\person_dict.txt")  # 加载用户字典
character = ['\xa0', ' ', '，']  # 去除的字符串
entity = ['n', 'm', 'nr', 'x', 'ns', 'v', 'l', 'nz']  # 可能是实体的词性
addition = ['n', 'nr', 'x', 'ns', 'v', 'nz']  # 可以进行合并的词性


def create_spo(detail_content):
    """
    将句子解析成spo三元组
    :param detail_content: 
    :return: 
    """
    # print(detail_content)
    spo = []
    '''  反向匹配构建spo三元组  '''
    for index in range(len(detail_content) - 1, -1, -1):
        # print(detail_content[index])
        address = detail_content[0]
        if (detail_content[index].flag is 'x') and (detail_content[index - 1].flag is 'x') and index >= 0:
            spo.append((address.word, detail_content[index - 2].word + detail_content[index].word))
        if (detail_content[index].flag in addition) and (detail_content[index - 1].flag in addition) and index >= 0:
            # spo.append(((address.word, address.flag), (detail_content[index - 1].word, " ", detail_content[index].word)))
            spo.append((address.word, detail_content[index - 1].word + detail_content[index].word))
    print(spo)


def cut_word(sentence):
    """
    分词处理  获取词语和词性
    :param sentence: 
    :return: 
    """
    new_result = []
    result = pesg.lcut(sentence)
    # print(result)
    cut_list = read_cut(path_file_cut)
    # 对结巴处理后的一个筛选
    for i in result:
        if (i.flag in entity) and (i.word not in cut_list) and (i.word not in character):
            new_result.append(i)
    create_spo(new_result)


def read_cut(path):
    """
    获取停用词
    :param path: 
    :return: 
    """
    cut_word_list = []
    with open(path, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    for line in lines:
        cut_word_list.append(line.strip())
    return cut_word_list


def read_file(path):
    """
    读取处理文件
    :param path: 
    :return: 
    """
    with open(path, 'r', encoding='utf-8') as f:
        lines = f.readlines()
    for line in lines:
        cut_word(line.strip())


if __name__ == '__main__':
    path_file_cut = r".\load_file\cut_word.txt"
    path_file_toc = r".\origin_file\part_1.txt"
    read_file(path=path_file_toc)
