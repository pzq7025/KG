# -*- coding: utf-8 -*-
from pyltp import SentenceSplitter
from pyltp import Segmentor
from pyltp import Postagger
from pyltp import NamedEntityRecognizer
from pyltp import Parser


def sentence_splitter(sentence):
    """
    分句，也就是将一片文本分割为独立的句子
    :param sentence:几句话
    :return: 单个单个句子
    """
    single_sentence = SentenceSplitter.split(sentence)  # 分句
    print
    '\n'.join(single_sentence)


def word_splitter(sentence):
    """
    分词
    :param sentence:
    :return:
    """
    segmentor = Segmentor()  # 初始化实例
    segmentor.load('D:/Program Files/ltp-models/3.3.1/ltp-data-v3.3.1/ltp_data/cws.model')  # 加载模型
    words = segmentor.segment(sentence)  # 分词
    # 默认可以这样输出
    # print '\t'.join(words)
    # 可以转换成List 输出
    words_list = list(words)
    # for word in words_list:
    #     print word
    segmentor.release()  # 释放模型
    return words_list


def word_tag(words):
    """
    词性标注
    :param words: 已切分好的词
    :return:
    """
    postagger = Postagger()  # 初始化实例
    postagger.load('D:/Program Files/ltp-models/3.3.1/ltp-data-v3.3.1/ltp_data/pos.model')  # 加载模型
    postags = postagger.postag(words)  # 词性标注
    # for word, tag in zip(words, postags):
    #     print word+'/'+tag
    postagger.release()  # 释放模型
    return postags


def name_recognition(words, postags):
    """
    命名实体识别
    :param words:分词
    :param postags:标注
    :return:
    """
    recognizer = NamedEntityRecognizer()  # 初始化实例
    recognizer.load('D:/Program Files/ltp-models/3.3.1/ltp-data-v3.3.1/ltp_data/ner.model')  # 加载模型
    netags = recognizer.recognize(words, postags)  # 命名实体识别

    # 地名标签为 ns
    result = ''
    for i in range(0, len(netags)):
        if i < len(words) - 2:
            if 's' in netags[i]:
                if 'O' in netags[i + 1] and words[i + 1] != ',' and words[i + 1] != '，':
                    if 's' in netags[i + 2]:
                        result += words[i] + words[i + 1] + words[i + 2] + " "
    print
    result
    # for word, ntag in zip(words, netags):
    #     print word + '/' + ntag
    recognizer.release()  # 释放模型
    return netags


def parse(words, postags):
    """
    依存句法分析
    :param words:
    :param postags:
    :return:
    """
    parser = Parser()  # 初始化实例
    parser.load('D:/Program Files/ltp-models/3.3.1/ltp-data-v3.3.1/ltp_data/parser.model')  # 加载模型
    arcs = parser.parse(words, postags)  # 句法分析
    print
    "\t".join("%d:%s" % (arc.head, arc.relation) for arc in arcs)
    parser.release()  # 释放模型


# 测试命名实体识别
print('命名实体识别')
words = word_splitter('小明你知道中国首都北京吗？当然我还知道韩国首都首尔')
tags = word_tag(words)
name_recognition(words, tags)
# parse(words,tags)
