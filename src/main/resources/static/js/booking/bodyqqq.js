function checkPasswordStrength(password) {
    if (password.length < 8) {
      return 'Weak';
    } else if (password.length >= 8 && password.length < 12) {
      return 'Medium';
    } else {
      return 'Strong';
    }
  }
  
  // Event listener to update the password strength indicator
  const passwordField = document.getElementById('signupPassword');
  const strengthIndicator = document.getElementById('strength-indicator');
  
  passwordField.addEventListener('input', e => {
    const password = e.target.value;
    const strength = checkPasswordStrength(password);
    strengthIndicator.textContent = strength;
    strengthIndicator.className = strength;
  });