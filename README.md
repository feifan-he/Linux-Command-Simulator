Example usage:
```
Welcome to the Unix-ish command line.

$ ls
pa1.iml
.DS_Store
target
pom.xml
.gitignore
.git
.idea
src

$ ls > dirs.txt

$ cat dirs.txt | wc
9 9 59

$ cat dirs.txt | grep . | grep git > gitfiles.txt

$ cat gitfiles.txt
.gitignore
.git

$ pwd
/Users/feifan/Desktop/Shell-Simulator
```