'''
读出文本文件，统计单词数输出
读出文本文件，随机输出其中的10个单词
'''
import random

f = open("02text.txt", 'r')
str_list = f.readlines()
word_list = []
for s in str_list:
    word_list.extend(s.split())
print("word count: %d" % (len(word_list)))
print("random choice:", end=' ')
print(random.sample(word_list, 10))