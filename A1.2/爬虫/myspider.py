import requests
from bs4 import BeautifulSoup
import json
import time

'''
1.获得所有的类别和该类别的书籍链接
2.进入书籍链接，爬取信息并保存
'''
url = 'http://njdxydjd.mh.libsou.com/'
start_time = time.time()

homepage = requests.get('http://njdxydjd.mh.libsou.com/newbook?pageno=2&cid=27')
soup = BeautifulSoup(homepage.text, 'html.parser')
sidemenu = soup.find_all('div', "sideMenu")
t = sidemenu[0]
t = t.find_all('a')
count = 0
page_dict = {}
for i in range(int(len(t) / 3)):
    page_dict.update({t[i * 3].contents[0] + '-' + t[i * 3 + 1].contents[0]: t[i * 3 + 1]['href']})
    page_dict.update({t[i * 3].contents[0] + '-' + t[i * 3 + 2].contents[0]: t[i * 3 + 2]['href']})

for catagory in page_dict.keys():  # 标准化url
    splitlist = page_dict[catagory].split('?')
    page_dict[catagory] = splitlist[0] + '?' + 'pageno=1&' + splitlist[1]


book_info = {'类别': '', '名称': '', '作者': '', '出版社': '', '出版时间': '', '页数': '', 'ISBN号': '', '简介': ''}

for catagory in page_dict.keys():
    # 大循环书类，每一类书下再循环页数读取
    book_info['类别'] = catagory
    while 1:
        page = requests.get(url + page_dict[catagory])
        soup = BeautifulSoup(page.text, 'html.parser')
        booklist = soup.find('div', 'bookList')
        for b in booklist.find_all('a'):
            bookpage = requests.get(url+b['href'])
            book_soup = BeautifulSoup(bookpage.text, 'html.parser')
            b_details = book_soup.find('dl')
            book_info['名称'] = b_details.dt.contents[0]
            next_tag = b_details.dd
            if next_tag.span.contents:
                book_info['作者'] = next_tag.span.contents[0]
            next_tag = next_tag.next_sibling.next_sibling
            if next_tag.span.contents:
                book_info['出版社'] = next_tag.span.contents[0]
            next_tag = next_tag.next_sibling.next_sibling
            if next_tag.span.contents:
                book_info['出版时间'] = next_tag.span.contents[0]
            next_tag = next_tag.next_sibling.next_sibling
            if next_tag.span.contents:
                book_info['页数'] = next_tag.span.contents[0]
            next_tag = next_tag.next_sibling.next_sibling
            if next_tag.span.contents:
                book_info['ISBN号'] = next_tag.span.contents[0]
            next_tag = next_tag.next_sibling.next_sibling
            intro = book_soup.find('div', id='content')
            temp_string = ''
            for j in intro.stripped_strings:
                temp_string += j
            book_info['简介'] = temp_string
            json_book = json.dumps(book_info, ensure_ascii=False)
            with open('Books\\bookdata.json', 'a+', encoding='gb18030', errors='ignore') as f:
                f.write(json_book)
            count += 1
            print(book_info)

        # 有无下一页？
        if soup.find('a', string="下一页"):
            nextpage_tag = soup.find('a', string="下一页")
            try:
                print(nextpage_tag['class'])
            except KeyError as e:
                current_pagenum = int(soup.find('span', 'currentpage').string)  # 当前页数
                splitlist = page_dict[catagory].split('?')
                sec_splitlist = splitlist[1].split('&')

                page_dict[catagory] = splitlist[0] + '?' + 'pageno=' + str(current_pagenum + 1) + '&' + sec_splitlist[1]
            else:
                print("Last page")
                break
        else:
            break
        
print('count %d' % count)
print("time cost: %fs" %(time.time() - start_time))
