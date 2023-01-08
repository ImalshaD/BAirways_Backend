function checkPasswordStrength(password) {
    if (password.length < 8) {
      return 'Weak';
    } else if (password.length >= 8 && password.length < 12) {
      return 'Medium';
    } else {
      return 'Strong';
    }
  }
  function checkPasswordConfirm(password) {
    if(passwordFieldConfirm.value==passwordField.value){
      return "Password matches";
    }else{
      return "Password does not match!"
    }
  }
    function login(){
        let userName=$('#loginName').val();
        let password=$('#loginPassword').val();
        $.ajax({
            method: "POST",
            contentType: "application/json",
            url: "/login",
            async: true,
            data: JSON.stringify({
                "username" : userName,
                "password" : password,
            }),
            success: function(data){
                console.log(data)
            },
            error :function(xhr,exception){
                console.error(error)
            }
        })
    }
    function saveUser(){
        try {
            let userName = document.getElementById("signupUserName").value;
            let firstName = document.getElementById("signupFirstName").value;
            let lastName = document.getElementById("signupLastName").value;
            let signUpEmail = document.getElementById("signupEmail").value;
            let password = document.getElementById("signupPassword").value;
            const xhr = new XMLHttpRequest();
            xhr.open("PUT", "http://localhost:8080/registerdUser/saveUser", true);
            xhr.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            xhr.onload = function() {
                if (xhr.status === 200) {
                    console.log("Success!");
                } else {
                    console.error("Error: " + xhr.status);
                }
            };
            let data = {
                "user_name": userName,
                "first_name": firstName,
                "last_name": lastName,
                "password": password,
                "type_id": 1,
                "email" : signUpEmail
            };
            xhr.send(JSON.stringify(data));
        }catch (e) {
            alert(e.toString())
        }
    }
  
  // Event listener to update the password strength indicator
  const passwordField = document.getElementById('signupPassword');
  const strengthIndicator = document.getElementById('strength-indicator');
  const passwordFieldConfirm = document.getElementById('signupPasswordConfirm');
  const ConfirmIndicator = document.getElementById('confirm-indicator');
  const LoginButton = document.getElementById('signupButton');
  passwordField.addEventListener('input', e => {
    const password = e.target.value;
    const strength = checkPasswordStrength(password);
    strengthIndicator.textContent = strength;
    strengthIndicator.className = strength;
  });
 
   // Event listener to update the password Confirm
   passwordFieldConfirm.addEventListener('input', e => {
    const password = e.target.value;
    const confirm = checkPasswordConfirm(password);
    ConfirmIndicator.textContent = confirm;
    if (confirm=="Password does not match!"){
      ConfirmIndicator.className='Weak';
      LoginButton.disabled=true;
    }else{
      ConfirmIndicator.className='Strong';
        LoginButton.disabled=false;
    }
  });