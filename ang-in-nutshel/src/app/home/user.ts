export class UserInfo {


    constructor(public id: number, public fullname: string, public mobile: string, public email: string, public datOfBirth: Date, public address: string, public userTitle: string) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.address = address;
        this.datOfBirth = datOfBirth;
        this.mobile = mobile;
        this.userTitle = userTitle;
    }

}
