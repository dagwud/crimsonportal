# JDK #

  * Download and install the latest Java SDK [here](http://java.sun.com/javase/downloads/index.jsp)
    * [Installation Instructions](http://java.sun.com/javase/6/webnotes/install/index.html)

# IDE #

  * Download the Netbeans 6.1 IDE (Java SE version, 31MB) [here](http://download.netbeans.org/netbeans/6.1/final/)

  * Download the JOGL plugin for Netbeans (17.6MB) [here](http://plugins.netbeans.org/PluginPortal/faces/PluginDetailPage.jsp?pluginid=3260)

  * Install Netbeans IDE
```
cd [downloaddir]
sudo ./netbeans-6.1-ml-javase-linux.sh
```

  * Install the JOGL plugins
    * Extract the JOGL plugins
```
mkdir ~/Desktop/temp
unzip netbeans-opengl-pack_0.5.2.zip -d ~/.netbeans/6.1/
```
    * Launch Netbeans
    * Open the plugin manager (Tools -> Plugins)
    * Click the "Downloaded" tab then "Add Plugins"
    * Add and install all the `.nbm` files in the .netbeans/6.1 directory

# Subversion #
## Checkout ##
  * Launch Netbeans, click Versioning > Subversion > Checkout
  * Provide the following details:
    * Repository URL: https://crimsonportal.googlecode.com/svn/trunk/
    * User: your gmail user, without the @gmail.com
    * Password: Your SVN password (see http://code.google.com/hosting/settings) - Note: this is not your gmail password
  * Click Next
  * Provide the following details:
    * Repository folders: crimsonportal
    * Repository Revision: (Leave empty)
    * Choose a local folder, and tick "Scan for Netbeans Projects after Checkout"
  * Click Finish
  * Click "Open Project"

## Commit ##
  * Click Versioning > Subversion > Commit...
  * Verify the files to be committed, and provide a commit comment
  * Click Commit