class PluginCheck{
  String key;
  BigDecimal pluginVersion;
  def internalPlugin;
  
  PluginCheck(String artifactId, String version){
    key = artifactId;
    pluginVersion = new BigDecimal(version);
    internalPlugin = new ArrayList<String>(Jenkins.instance.pluginManager.plugins).find{ x -> x.shortName == key };
      }
  
  String shortName(){
    return internalPlugin.shortName;
  }
  
  String installedVersion(){
    return internalPlugin.version;
  }
  
  Boolean isInstalled(){
   return internalPlugin != null && new BigDecimal(internalPlugin.version.split("\\.")[0]) >= this.pluginVersion; 
  }
  
  void install(){
    Jenkins.instance.updateCenter.getPlugin(key).deploy();
  }
}

def pluginCheck = new PluginCheck("jobConfigHistory", "1207");


if (!pluginCheck.isInstalled()){
  pluginCheck.install();
}
else{
  println(pluginCheck.isInstalled() ? "Is Installed" : "Not Installed");
  println(pluginCheck.shortName());
  println(pluginCheck.installedVersion());
}


