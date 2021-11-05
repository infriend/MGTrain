import json
import os

from flask import Flask, render_template, request, flash, session, redirect, jsonify
import pymysql
import hashlib

app = Flask(__name__)
app.secret_key = os.urandom(16)


@app.route('/')
def loginpage():
    return render_template("login.html")


@app.route("/admin/login", methods=['GET', 'POST'])
def admin_login():
    username = request.form['username']
    password = request.form['passwd']
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    cursor.execute("select * from admin_account where username='%s'" % username)
    result = cursor.fetchone()
    if result == None:
        msg = {"msg": "用户名不存在"}
    else:
        if result[0] == username:
            password = hashlib.md5(password.encode('UTF-8')).hexdigest()
            if password != result[1]:
                msg = {"msg": "密码错误"}
            else:
                msg = {"msg": "yes"}
                session['username'] = 'admin'
    js_msg = jsonify(msg)
    conn.close()
    return js_msg


@app.route("/manager/login", methods=['GET', 'POST'])
def manager_login():
    username = request.form['username']
    password = request.form['passwd']
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    cursor.execute("select * from hotel_admin where username='%s'" % username)
    result = cursor.fetchone()
    if result == None:
        msg = {"msg": "用户名不存在"}
    else:
        if result[0] == username:
            password = hashlib.md5(password.encode('UTF-8')).hexdigest()
            if password != result[1]:
                msg = {"msg": "密码错误"}
            else:
                msg = {"msg": "yes"}
                session['username'] = username
    js_msg = jsonify(msg)
    conn.close()
    return js_msg


@app.route("/admin/adminhome", methods=['GET', 'POST'])
def adminhome():
    if 'username' not in session or session['username'] != "admin":
        return redirect("/")
    return render_template("admin_index.html")


@app.route("/manager/managerhome", methods=['GET', 'POST'])
def managerhome():
    if 'username' not in session or session['username'] == "admin":
        return redirect("/")
    return render_template("manager_index.html")


@app.route("/admin/addmanagerpage", methods=['GET', 'POST'])
def addmanagerpage():
    if 'username' not in session or session['username'] != "admin":
        return redirect("/")
    return render_template("manager-add.html")


@app.route("/admin/addmanager", methods=['GET', 'POST'])
def addmanager():
    if 'username' not in session or session['username'] != "admin":
        return redirect("/")
    username = request.form['username']
    password = request.form['passwd']
    hotelname = request.form['hotelname']
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    cursor.execute("select * from hotel_admin where username='%s' and hotelname='%s'" % (username, hotelname))
    result = cursor.fetchone()
    if result is None:
        md5password = hashlib.md5(password.encode('UTF-8')).hexdigest()
        try:
            cursor.execute('insert into hotel_admin(username, hotelname, passwd) '
                           'values ("%s", "%s", "%s")' % (username, hotelname, md5password))
            conn.commit()
            msg = {"msg": "yes"}
        except:
            conn.rollback()
            msg = {"msg": "no"}
    else:
        msg = {"msg": "no"}
    conn.close()
    msg = jsonify(msg)
    return msg


@app.route("/admin/addhotelpage", methods=['GET', 'POST'])
def addhotelpage():
    if 'username' not in session or session['username'] != "admin":
        return redirect("/")
    return render_template("hotel-add.html")


@app.route("/admin/addhotel", methods=['GET', 'POST'])
def addhotel():
    if 'username' not in session or session['username'] != "admin":
        return redirect("/")
    hotelname = request.form['hotelname']
    addr = request.form['addr']
    intro = request.form['intro']
    tel = request.form['tel']
    shop = request.form['shop']
    star = request.form['star']
    parklot = int(request.form['parklot'])
    elec_park = int(request.form['elec_park'])
    wifi = int(request.form['wifi'])
    wakeservice = int(request.form['wakeservice'])
    luggage = int(request.form['luggage'])
    elevator = int(request.form['elevator'])
    aircon = int(request.form['aircon'])
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    cursor.execute("select * from hotel where hotelname='%s'" % hotelname)
    result = cursor.fetchone()
    if result is None:
        try:
            cursor.execute(
                'insert into hotel(hotelname, addr, intro, tel, shop, star, parklot, elec_park, wifi, wakeservice, luggage, elevator, aircon) '
                'values ("%s", "%s", "%s", "%s", "%s", "%s", %d, %d, %d, %d, %d, %d, %d)'
                % (hotelname, addr, intro, tel, shop, star, parklot, elec_park, wifi, wakeservice, luggage, elevator,
                   aircon))
            conn.commit()
            msg = {"msg": "yes"}
        except:
            conn.rollback()
            msg = {"msg": "no"}
    else:
        msg = {"msg": "no"}
    conn.close()
    msg = jsonify(msg)
    return msg


@app.route("/manager/managerhotelinfo", methods=['GET', 'POST'])
def hotelinfo():
    if 'username' not in session or session['username'] == "admin":
        return redirect("/")
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    cursor.execute("select hotelname from hotel_admin where username='%s'" % session['username'])
    hotelname = cursor.fetchone()
    cursor.execute("select * from hotel where hotelname='%s'" % hotelname)
    result = cursor.fetchone()
    conn.close()
    return render_template("hotel-edit.html", res=result)


@app.route("/manager/suitelist", methods=['GET', 'POST'])
def suiteinfo():
    if 'username' not in session or session['username'] == "admin":
        return redirect("/")
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    username = session["username"]
    cursor.execute("select hotelname from hotel_admin where username='%s'" % session['username'])
    hotelname = cursor.fetchone()
    cursor.execute("select * from suite_info where hotelname='%s'" % hotelname)
    result = cursor.fetchall()
    conn.close()
    return render_template("suite-list.html", data=result)


@app.route("/manager/suiteedit", methods=['GET', 'POST'])
def suiteedit():
    if 'username' not in session or session['username'] == "admin":
        return redirect("/")
    hotelname = request.args['hotelname']
    suitetype = request.args['suitetype']
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    cursor.execute("select * from suite_info where hotelname='%s' and suitetype='%s'" % (hotelname, suitetype))
    result = cursor.fetchone()
    conn.close()
    return render_template("suite-edit.html", res=result)

@app.route("/manager/suiteeditprocess", methods=['GET', 'POST'])
def suiteeditprocess():
    if 'username' not in session or session['username'] == "admin":
        return redirect("/")
    suitetype = request.form['suitetype']
    price = int(request.form['price'])
    num = int(request.form['count'])
    conn = pymysql.connect(host='localhost', user='root', passwd='12345678', db='hotelsys')
    cursor = conn.cursor()
    username = session["username"]
    cursor.execute("select hotelname from hotel_admin where username='%s'" % session['username'])
    hotelname = cursor.fetchone()
    try:
        cursor.execute("update suite_info set suitetype='%s', price=%d, num=%d where suitetype='%s' and hotelname='%s'" %(suitetype, price,num,suitetype,hotelname[0]))
        conn.commit()
        msg = {"msg": "yes"}
    except:
        conn.rollback()
        msg = {"msg": "no"}
    conn.close()
    msg = jsonify(msg)
    return msg


if __name__ == '__main__':
    app.run()
