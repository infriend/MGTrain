'''
〉 创建一个类People
包含属性name, city
可以转换为字符串形式（__str__）
包含方法moveto(self, newcity)
可以按照city排序
创建4个人对象，放到列表进行排序
'''


class People:
    def __init__(self, name, city):
        self.name, self.city = name, city

    def __str__(self):
        return '<' + self.name + '>: ' + self.city

    def moveto(self, newcity):
        self.city = newcity

    def __lt__(self, other):  # self less than other x < y call this method
        return self.city < other.city  # in sort() the least one should be in the first position


a1 = People('a', 'Beijing')
a2 = People('b', 'Tokyo')
a3 = People('c', 'Shanghai')
a4 = People('d', 'Nanjing')

p_list = [a1, a2, a3, a4]
p_list.sort()
for i in p_list:
    print(i)

'''
创建一个类Teacher
是People的子类，新增属性school
moveto方法改为newschool
按照school排序
创建4个教师对象，放到列表进行排序
'''


class Teacher(People):
    def __init__(self, name, city, school):
        super().__init__(name, city)
        self.school = school

    def moveto(self, newschool):
        self.school = newschool

    def __lt__(self, other):
        return self.school < other.school

    def __str__(self):
        return '<' + self.name + '>: ' + self.city + ', ' + self.school


a1 = Teacher('a', 'Beijing', 'PKU')
a2 = Teacher('b', 'Tokyo', 'UT')
a3 = Teacher('c', 'Shanghai', 'SJTU')
a4 = Teacher('d', 'Nanjing', 'NJU')

t_list = [a1, a2, a3, a4]
t_list.sort()
for i in t_list:
    print(i)
