var fs = require('fs');
const bcrypt = require('bcrypt');
const saltRounds = 10;

exports.validateUser = function (username, password) {
  var data = fs.readFileSync('./users.json', 'utf8');
  data = JSON.parse(data);
	if (data.users && data.users[username])
  {
    var salt = data.users[username].salt;
    var hash = bcrypt.hashSync(password, salt);
    if (hash === data.users[username].password) {
		    return true;
    }
	}
  return false;
};

exports.addNewUser = function (username, password) {
  var data = fs.readFileSync('./users.json', 'utf8');
  console.log("Adding User");
  var salt = bcrypt.genSaltSync(saltRounds);
  var hash = bcrypt.hashSync(password, salt);
  //console.log(salt);
  //console.log(hash);
  var newUser = {
    "salt" : salt,
    "password" : hash
  };
  data = JSON.parse(data);
  if (data.users && !data.users[username]) {
    data.users[username] = newUser;
    fs.writeFileSync("./users.json", JSON.stringify(data), "utf8");
    console.log("Adding new user complete");
  }
};
