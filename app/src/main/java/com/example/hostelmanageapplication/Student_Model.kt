package com.example.hostelmanageapplication

class Student_Model {
    var name = ""
    var email = ""
    var mobileno = ""
    var address = ""
    var gender = ""
    var photo = ""
    var enrollment = ""
    var semester = 0
    var department = ""
    var fathername = ""
    var fathermobno = ""
    var bloodgroup = ""
    var college = ""
    var year = 0
    var dob = ""
    var hosteltype = ""
    var password = ""
    var confirmpassword = ""

    var hostelstatus = ""
    var hostelname = ""
    var roomno = 0
    var totalfee = ""
    var feepaid=""
    var feepending=""
    var dateofjoining = ""
    var dateofleaving=""
    var role=""

    constructor(name:String,email:String,mobileno:String,address:String,gender:String,photo:String,enrollment:String,semester:Int,department:String,fathername:String,fathermobno:String,
                bloodgroup:String,college:String,year: Int,dob:String,hosteltype:String,password:String,confirmpassword:String,hostelstatus:String,hostelname:String,totalfee:String,feepaid:String,feepending:String,dateofjoining:String,dateofleaving:String,role:String,roomno:Int)
    {
        this.name = name
        this.email = email
        this.mobileno = mobileno
        this.address = address
        this.gender = gender
        this.photo = photo
        this.enrollment = enrollment
        this.semester = semester
        this.department = department
        this.fathername = fathername
        this.fathermobno = fathermobno
        this.bloodgroup = bloodgroup
        this.college = college
        this.year = year
        this.dob = dob
        this.hosteltype = hosteltype
        this.password = password
        this.confirmpassword = confirmpassword

        this.hostelstatus = hostelstatus
        this.hostelname = hostelname
        this.totalfee = totalfee
        this.feepaid= feepaid
        this.feepending= feepending
        this.dateofjoining = dateofjoining
        this.dateofleaving= dateofleaving
        this.role= role
        this.roomno = roomno

    }

}