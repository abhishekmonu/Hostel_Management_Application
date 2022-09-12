package com.example.hostelmanageapplication

class Rector_Model {
    var name = ""
    var email = ""
    var mobileno = ""
    var address = ""
    var gender = ""
    var photo = ""
    var enrollment = ""
    var fathername = ""
    var fathermobno = ""
    var bloodgroup = ""
    var dob = ""
    var hostelname = ""
    var password = ""
    var confirmpassword = ""
    var occupation = ""
    var dateofjoining = ""
    var dateofleaving=""
    var role=""

    constructor(name:String,email:String,mobileno:String,address:String,gender:String,
                photo:String,enrollment:String, fathername:String,fathermobno:String,
                bloodgroup:String, dob:String,hostelname:String,password:String,confirmpassword:String,
                occupation: String,dateofjoining:String,dateofleaving:String,role:String)
    {
        this.name = name
        this.email = email
        this.mobileno = mobileno
        this.address = address
        this.gender = gender
        this.photo = photo
        this.enrollment = enrollment
        this.fathername = fathername
        this.fathermobno = fathermobno
        this.bloodgroup = bloodgroup
        this.dob = dob
        this.hostelname = hostelname
        this.password = password
        this.confirmpassword = confirmpassword
        this.occupation = occupation
        this.dateofjoining = dateofjoining
        this.dateofleaving= dateofleaving
        this.role= role
    }
}