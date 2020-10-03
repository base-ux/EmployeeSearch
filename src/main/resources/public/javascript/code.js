$(".carousel").carousel({
  interval: 10000
})

$(document).ready(function () {
    options = {
        common: {minChar:8},
        ui: {
            showVerdictsInsideProgressBar:true,
            showErrors:true,
            errorMessages:{
                wordLength: '<spring:message code="error.wordLength"/>',
                wordLowercase: '<spring:message code="error.wordLowercase"/>',
                wordUppercase: '<spring:message code="error.wordUppercase"/>',
                wordOneNumber: '<spring:message code="error.wordOneNumber"/>',
                wordOneSpecialChar: '<spring:message code="error.wordOneSpecialChar"/>'
            }
        }
    }
    $('#RegisterFormPassword').pwstrength(options)
})

$(document).ready(function () {
        var $signupButton = $("#signup")
        var $passwordField = $("#RegisterFormPassword")
        var $confirmPasswordField = $("#RegisterFormMatchingPassword")
        var $errorMessage = $('<small class="container-fluid form-text mt-4 text-center text-danger" id="error">confirm must match password</small>')

        function checkMatchingPassword () {
            if($confirmPasswordField.val() != $passwordField.val() && 
            $confirmPasswordField.val() != "" && $passwordField.val() != "") {
                    $errorMessage.insertAfter($confirmPasswordField)
                    $signupButton.attr("disabled", "disabled")
            }
        }

        function resetMatchingPasswordError () {
            $signupButton.removeAttr("disabled")
            var $errorMessageShow = $("#error")
            if($errorMessageShow.length > 0) {
                $errorMessageShow.remove()
            }
        }

        $("#RegisterFormMatchingPassword, #RegisterFormPassword")
            .on("keydown", function (err) {
                if(err.keyCode == 13 || err.keyCode == 9){
                    checkMatchingPassword()
                }
            })
            .on("blur", function () {
                checkMatchingPassword()
            })
            .on("focus", function () {
                resetMatchingPasswordError()
            })

        $signupButton.removeAttr("disabled")
})