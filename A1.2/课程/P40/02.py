'''
将一篇文章写入一个文本文件
'''

f = open("02text.txt", 'a+')
str_list = ["This article explains the new features in Python 3.7.\n", "Python 3.7 was released on June 27, 2018.\n",
            "For full details, see the changelog.\n"]
f.writelines(str_list)
f.close()

