// Welcome Page Carousel
$(".carousel").carousel({
  interval: 10000
})

// Home Page Drag and Drop
$(function() {
    $("#sortable").sortable()
    $("#sortable").disableSelection()
})

// Posts Page Summernote Text Editor
$(document).ready(function() {
    $("#summernote").summernote({
    height: 140,
    minHeight: 140,
    maxHeight: 800,
    toolbar: [
    [ 'fontname', [ 'fontname' ] ],
    [ 'fontsize', [ 'fontsize' ] ],
    [ 'color', [ 'forecolor', 'color' ] ],
    [ 'style', [ 'style' ] ],
    [ 'view', [ 'undo', 'redo' ] ],
    [ 'font', [ 'bold', 'italic', 'underline', 'strikethrough', 'superscript', 'subscript', 'hr', 'clear'] ],
    [ 'view', [ 'codeview' ] ],
    [ 'para', [ 'ol', 'ul', 'paragraph', 'height' ] ],
    [ 'table', [ 'table' ] ],
    [ 'insert', [ 'link'] ],
    [ 'view', [ 'fullscreen', 'help' ] ]
    ]
    })
})

// Register Page Password Confirmation
$(document).ready(function() {
    var $signupButton = $("#signup")
    var $passwordField = $("#RegisterFormPassword")
    var $confirmPasswordField = $("#RegisterFormMatchingPassword")
    var $errorMessage = $('<small class="container-fluid form-text mt-4 text-center text-danger" id="error">confirm must match password</small>')

    function checkMatchingPassword() {
        if($confirmPasswordField.val() != $passwordField.val() || 
        $confirmPasswordField.val() == "" && $passwordField.val() != "") {
                $errorMessage.insertAfter($confirmPasswordField)
                $signupButton.attr("disabled", "disabled")
        }
    }

    function resetMatchingPasswordError() {
        $signupButton.removeAttr("disabled")
        var $errorMessageShow = $("#error")
        if($errorMessageShow.length > 0) {
            $errorMessageShow.remove()
        }
    }

    $("#RegisterFormMatchingPassword, #RegisterFormPassword")
        .on("keydown", function(err) {
            if(err.keyCode == 13 || err.keyCode == 9){
                checkMatchingPassword()
            }
        })
        .on("blur", function() {
            checkMatchingPassword()
        })
        .on("focus", function() {
            resetMatchingPasswordError()
        })

    $signupButton.removeAttr("disabled")
})