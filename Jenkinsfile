 pipeline {
     agent any
     stages {
         stage('BUILD') {
             steps {
                 sh 'npm run build'
             }
         }

         stage('STOP App') {
             steps {
                 sh 'ssh root@nginx "rm -rf /root/nginx/front"'
             }
         }
         
         stage('Deployment') {
             steps {
                 sh 'scp -r ./build root@nginx:/root/nginx/front'
             }
         }
     }
 }