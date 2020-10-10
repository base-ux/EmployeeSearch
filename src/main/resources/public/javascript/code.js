$(".carousel").carousel({
  interval: 10000
})

$(document).ready(function () {
    var $signupButton = $("#signup")
    var $passwordField = $("#RegisterFormPassword")
    var $confirmPasswordField = $("#RegisterFormMatchingPassword")
    var $errorMessage = $('<small class="container-fluid form-text mt-4 text-center text-danger" id="error">confirm must match password</small>')

    function checkMatchingPassword () {
        if($confirmPasswordField.val() != $passwordField.val() || 
        $confirmPasswordField.val() == "" && $passwordField.val() != "") {
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

jQuery(function($) {
    var dragdropColumnLeft = $("#draggableLeft")

    dragdropColumnLeft.sortable({
        update: function() {
            $(".dragdrop", dragdropColumnLeft).each(function(index, element) {
                var $dragdropItemLeft = $(element),
                    newIndexLeft = $dragdropItemLeft.index()
            })
        }
    })
})

jQuery(function($) {
    var dragdropColumnRight = $("#draggableRight")

    dragdropColumnRight.sortable({
        update: function() {
            $(".dragdrop", dragdropColumnRight).each(function(index, element) {
                var $dragdropItemRight = $(element),
                    newIndexRight = $dragdropItemRight.index()
            })
        }
    })
})