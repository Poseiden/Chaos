pipeline {
    environment {
	GRADLE_IMAGE = 'gradle:6.1.1-jdk11'
    }

    agent any
    stages {
	stage('Test') {
	    steps {
		    sh '''
		        docker run --rm -v $(pwd):/opt/app -v ~/.gradle:/root/.gradle -w /opt/app $GRADLE_IMAGE /bin/bash -c "gradle clean test"
		       '''
	    }
	}
	stage('Build') {
	    steps {
		    sh '''
		        docker run --rm -v $(pwd):/opt/app -v ~/.gradle:/root/.gradle -w /opt/app $GRADLE_IMAGE /bin/bash -c "gradle clean build"
		       '''
	    }
	}
	/*Below need docker plugin for jenkins support*/
	/*stage('Build Image') {*/
	    /*steps {*/
                /*script {*/
		 /*Need docker plugin for jenkins support */
			/*dockerImage = docker.build(REGISTRY)*/
                        /*docker.withRegistry('https://your_registry', your_credential ) {*/
                            /*dockerImage.push()*/
			/*}*/
                /*}*/
	    /*}*/
	/*}*/

    }
}
