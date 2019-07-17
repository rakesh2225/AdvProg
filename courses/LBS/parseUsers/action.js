var userValidator = require('./userManager');
userValidator.addNewUser("pphung", "12345");
userValidator.addNewUser("rreddy", "12345");
userValidator.addNewUser("bhagya", "12345");
console.log(userValidator.validateUser("bhagya", "12345"));
console.log(userValidator.validateUser("bhagya1", "12345"));
