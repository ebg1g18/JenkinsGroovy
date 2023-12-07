import hudson.model.*
import hudson.security.*
  
class UserManager{
Set allUsers(){ 
	return User.getAll()
}  

  void createUser(String userName, String password){
    if (!this.userExists(userName)){
      Jenkins instance = Jenkins.getInstance();
      def realm = new HudsonPrivateSecurityRealm(false);
      
      realm.createAccount(userName, password);
      instance.setSecurityRealm(realm);
      instance.save();
    }
  }
   
  Boolean userExists(userName){
      return User.get(userName) != null;
  }
  
  
  void deleteUser(String id){
    if (this.userExists(id)){
       User u = User.get(id);
       u.delete();
    }
  }
  
}

def mgr = new UserManager();

mgr.createUser("test", "user");
mgr.deleteUser("test");

for (user in mgr.allUsers()){
  println(user.id);
}
