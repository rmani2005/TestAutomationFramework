pipeline 
{
    agent any
   
    stages
    {
        stage ('Initialize the Maven')
        {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }
        stage ('Build the maven project with testing parameter') 
        {
            steps 
            {
                //env.environment
                //env.module
                //env.exeType
                //env.
                sh 'mvn clean test -Dmaven.test.failure.ignore=true'
                
        
            }
         }
         stage ('Starting ART job') 
         {
    		build job: 'APITestFramework'            
            post 
            {
  	 			success 
                    {
	                archiveArtifacts artifacts:'Report/**/**/*.html', fingerprint: true
                    archiveArtifacts artifacts:'target/surefire-reports/index.html', fingerprint: true
                    echo 'Successfully!'
                    
                    }
                
		        failure {
		            echo 'Failed!'
		        }
		        unstable {
		            echo 'This will run only if the run was marked as unstable'
		        }
		        changed {
		            echo 'This will run only if the state of the Pipeline has changed'
		            echo 'For example, if the Pipeline was previously failing but is now successful'
		        }
                
            }
            
            
            
        }
        
    }
}
