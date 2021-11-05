'''
〉 给定y和m，计算y年m月有几天？
'''

y = int(input("Y"))
m = int(input("m"))
d1 = {
    1: 31, 2: 28, 3: 31, 4: 30, 5: 31, 6: 30, 7: 31,
    8: 31, 9: 30, 10: 31, 11: 30, 12: 31
}
if y % 4 == 0 and y % 100 != 0:
    if m == 2:
        print(29)
    else:
        print(d1[m])
elif y % 400 == 0:
    if m == 2:
        print(29)
    else:
        print(d1[m])
else:
    print(d1[m])
