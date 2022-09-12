package com.example.hostelmanageapplication

class Leaving_Model {
    var enroll=""
    var name=""
    var sem=""
    var college = ""
    var roomno=""
    var hostelname = ""
    var date=""
    var purpose= ""
    var Address=""
    var return_date=""
    var time=""

    constructor(enroll:String,name:String,sem:String,college:String,roomno:String,hostelname:String,date:String,purpose:String,
                Address:String,return_date:String,time:String)
    {
        this.enroll = enroll
        this.name = name
        this.sem = sem
        this.college=college
        this.roomno = roomno
        this.hostelname = hostelname
        this.date = date
        this.purpose = purpose
        this.Address = Address
        this.return_date = return_date
        this.time = time
    }
}