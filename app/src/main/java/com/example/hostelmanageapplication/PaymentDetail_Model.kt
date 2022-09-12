package com.example.hostelmanageapplication

class PaymentDetail_Model {

    var account=""
    var ifsc=""
    var branch=""
    var bankname=""

    constructor(account:String,ifsc:String,branch:String,bankname:String)
    {
        this.account = account
        this.ifsc = ifsc
        this.branch = branch
        this.bankname = bankname
    }
}