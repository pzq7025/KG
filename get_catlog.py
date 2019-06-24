# import jieba
# import jieba.posseg
# import os
# import jiagu
import re


# from pyhanlp import *


def get(path_file=None):
    result = []
    with open(path_file, 'r', encoding='utf-8') as f:
        for line in f.readlines():
            result.append(line.strip())
    f.close()
    return result


# def detail(path_file):
#     content = get(path_file)
#     word = jiagu.seg(content)
#     # word_char = jiagu.knowledge(content)
#     print(word)
def cut_word(path_file=None):
    result = []
    with open(path_file, 'r', encoding='utf-8') as f:
        for line in f.readlines():
            result.append(line.strip())
    f.close()
    return result


if __name__ == '__main__':
    # jieba.load_userdict(r".\load_file\person_dict.txt")
    # texts = get(r".\origin_file\part_1.txt")
    # cuts = cut_word(r".\load_file\cut_word.txt")
    # for text in texts:
    #     s = jieba.lcut(text)
    #     for cut in cuts:
    #         if cut not in s:
    #             pass
    # text = "计算机应用的主要发展方向"
    # print(HanLP.segement(text))
    # text = "whut是武汉市成立的第一所大学，首先它成立于1998年12月距今已经有5000年的历史了，whut的发展取决于计算机学院的大力支持和不懈努力，因此whut天下第一" \
    text = "whut的网址是https://whut.edu.com, 它的连接是http://whut.edu.cn,它是华师的女朋友,华师的连接是：htt://whut.edu.com/refigner/asfsdx/ppt.html" \
           "还有一个网站是https://www.baidu.com/"
    part_n = re.compile(r"[a-zA-Z]+://[a-zA-Z]+\.[a-zA-Z]+\.[com|cn]+", re.S)
    part_s = re.compile(r"[a-zA-Z]+://[a-zA-Z]+\.[a-zA-Z]+\.[com|cn]+/.*?\.[html]+", re.S)
    result = re.findall(part_n, text)
    result_1 = re.findall(part_s, text)
    print(result_1, result)
    # knowledge = jiagu.knowledge(text)
    # print(knowledge)
    # [a-zA-Z]+://[^\s]*[.com|.cn]
    # web_address = re.compile(r"[a-zA-Z]+://[a-zA-Z]+.[a-zA-Z]+.*[.com|.cn|.html]")
    #     # result_n = re.findall(web_address, text)
    #     # print(result_n)
    # result = jieba.lcut(text,)
    # print(result)
    # result = jieba.cut(text, cut_all=True)
    # print(' '.join(result))
