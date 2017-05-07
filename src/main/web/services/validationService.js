app.service('validationService', function () {

    this.validateUser = function(user){
        if (!user.email){
            return {
                statusType: 'ERROR',
                message: "Lack of login"
            }
        }

        if(!user.password){
            return {
                statusType: 'ERROR',
                message: "Lack of password"
            }
        }

        return {
            statusType: 'OK',
            message: ''
        }
    };

    this.validateUserBeforeRegistration = function (user) {
        return this.validateUser(user);
    };

});