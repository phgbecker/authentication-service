/**
 * Global variables
 */
const loadingRequest = '<img src=\"./images/loading.gif\" /> Loading...';

$(document).ready(function() {  
    /**
     * Loading transitions
     */
    $('#signInBox').fadeIn(500);
    $('#usernameInput').focus();

    $('#signInForm').submit(function (event) {        

        /**
         * Consume the default form event to avoid propagation
         */
        event.preventDefault();

            /**
             * Validate a series of user inputs before signing in
             */
            if (isSignInFormValid()) {
                /**
                 * Build the "User" JSON object
                 */
                const User = '{ "username" : "' + $('#usernameInput').val() + '", "password" : "' + $('#passwordInput').val() + '" }';
                console.log(User);

                /**
                 * Tries to SignIn
                 */
                doSignIn(User);
            }
    });

});

/**
 * Sign in form validation method
 * @returns boolean
 */
function isSignInFormValid() {
    if (!$('#usernameInput').val()) {        
        $('#usernameValidation').text('Login field is mandatory').show().fadeOut(1500);
        $('#usernameInput').focus();

        return false;
    }

    if ($('#usernameInput').val().length <= 3) {        
        $('#usernameValidation').text('Login field length should be bigger than 3').show().fadeOut(1500);
        $('#usernameInput').focus();

        return false;
    }

    if (!$('#passwordInput').val()) {        
        $('#passwordValidation').text('Password field is mandatory').show().fadeOut(1500);
        $('#passwordInput').focus();

        return false;
    }

    if ($('#passwordInput').val().length <= 5) {        
        $('#passwordValidation').text('Password field length should be bigger than 5').show().fadeOut(1500);
        $('#passwordInput').focus();

        return false;
    }

    return true;
}

/**
 * Sign in authentication method
 * @param {JSON} User 
 */
function doSignIn(User) {    
    $('#signInValidation').empty().show().css('color', 'black').append(loadingRequest);

    $.ajax({
        url: 'authentication',
        dataType: 'html',
        type: 'POST',
        data: User,
        contentType: 'application/json;charset=UTF-8',
        success: function(data, textStatus) {
            $('#signInValidation').html(data);
        }
    });    
}
