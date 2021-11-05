'''
轻微改变图片中像素的RGB值，肉眼无法
察觉
〉 将8bit R/G/B中的最低1bit，用于隐藏一
个数据文件（如文本）
每3个像素可以隐藏1个字节
〉 注意使用不失真图像格式
BMP／PNG格式
〉 要求：
1）提供图片和数据文件，生成隐藏信息的图片
2）从隐藏信息的图片中提取数据文件
'''
import numpy as np
from PIL import Image

'''
前三个像素的三个通道记录字符串长度，抛弃最后一个通道，最长256个字符，字符均为英文，使用ascii编码，每个字符一字节，因此最多读取257 * 3个像素
使用的图像width>900，均处于图像的第一行
'''
message = "This is a piece of hidden message!"


def getbinstr(num):
    t = format(num, 'b')
    return (8 - len(t)) * '0' + t
# 获得二进制数字符串，长度8

bi_list = []
for s in message:
    bi_list.append(getbinstr(ord(s)))


def hide_message(str):
    str_binlen = getbinstr(len(str))

    img = Image.open("image.png")
    arr1 = np.array(img)

    for i in range(8):
        if str_binlen[i] == '1':
            arr1[0][i // 3][i % 3] |= 1
        else:
            arr1[0][i // 3][i % 3] &= 254

    for i in range(len(str)):
        ascii_num = getbinstr(ord(str[i]))
        for j in range(8):
            if ascii_num[j] == '1':
                arr1[0][i * 3 + 3 + j // 3][j % 3] |= 1
            else:
                arr1[0][i * 3 + 3 + j // 3][j % 3] &= 254


    img2 = Image.fromarray(arr1).convert('RGB')

    img2.save('temp.png')


def read_information():
    timg = Image.open("image.png")
    a = np.array(timg)
    img = Image.open("temp.png")
    arr1 = np.array(img)

    m_len = ''
    for i in range(8):
        if arr1[0][i // 3][i % 3] & 1 == 1:
            m_len += '1'
        else:
            m_len += '0'

    m_len = int(m_len, 2)
    message_list = []

    for i in range(m_len):
        temp_str = ''
        for j in range(8):
            if arr1[0][i * 3 + 3 + j // 3][j % 3] & 1 == 1:
                temp_str += '1'
            else:
                temp_str += '0'
        message_list.append(temp_str)

    str = ''
    for s in message_list:
        str += chr(int(s, 2))
    print(str)


hide_message(message)
read_information()
