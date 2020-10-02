$(".carousel").carousel({
  interval: 10000
})

$(document).ready(function(){
        var $signupButton = $("#signup")
        var $passwordField = $("#RegisterFormPassword")
        var $confirmPasswordField = $("#RegisterFormMatchingPassword")
        var $errorMessage = $('<div class="container-fluid"><small class="form-text mt-4 text-center text-danger" id="error">confirm must match password</small></div>')

        $signupButton.removeAttr("disabled")

        function checkMatchingPasswords(){
            if($confirmPasswordField.val() != "" && $passwordField.val != ""){
                if($confirmPasswordField.val() != $passwordField.val()){
                    $signupButton.attr("disabled", "disabled")
                    $errorMessage.insertAfter($confirmPasswordField)
                }
            }
        }

        function resetMatchingPasswordsError(){
            $signupButton.removeAttr("disabled")
            var $errorMessageShow = $("#error")
            if($errorMessageShow.length > 0){
                $errorMessageShow.remove()
            } 
        }

        $("#RegisterFormMatchingPassword, #RegisterFormPassword")
            .on("keydown", function(e){
                if(e.keyCode == 13 || e.keyCode == 9){
                    checkMatchingPasswords()
                }
            })
            .on("blur", function(){
                checkMatchingPasswords()
            })
            .on("focus", function(){
                resetMatchingPasswordsError()
            })
})