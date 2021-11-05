'''
编写程序，输入两个数，输出它们的
商，采用例外处理来处理两种错误，
给出用户友好的提示信息
1）除数为0
2）输入了非数值
'''


while 1:
    try:
        a = float(input("Num A: "))
        b = float(input("Num B: "))
        print(a/b)
    except ZeroDivisionError as e:
        print("除数为0", e)
    except ValueError as e:
        print("输入了非数值", e)
    else:
        break
