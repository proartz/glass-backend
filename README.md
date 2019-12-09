# glass

Deployment using maven appengine
1. Prepare proper POM.xml:

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-gcp-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-gcp-starter-sql-mysql</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        
        and
        
        <dependencyManagement>
            <dependencies>
                <dependency>
                    <groupId>org.springframework.cloud</groupId>
                    <artifactId>spring-cloud-dependencies</artifactId>
                    <version>${spring-cloud.version}</version>
                    <type>pom</type>
                    <scope>import</scope>
                </dependency>
            </dependencies>
        </dependencyManagement>
        
        and in build
        
        <plugin>
            <groupId>com.google.cloud.tools</groupId>
            <artifactId>appengine-maven-plugin</artifactId>
            <version>1.3.0</version>
        </plugin>
        
        and
        
         <repositories>
            <repository>
                <id>spring-milestones</id>
                <name>Spring Milestones</name>
                <url>https://repo.spring.io/milestone</url>
            </repository>
        </repositories>
        
The best option to prepare the POM.xml is to use spring initilzr.

2. Enable gcp profile in application.properties

    spring.profiles.active=gcp
    
application-gcp.properties should look like:

    spring.cloud.gcp.sql.instance-connection-name=glassbackend:europe-west2:glassbackend-db
    
to set up the CLOUD SQL instance-connection
    
    spring.cloud.gcp.sql.database-name=glassbackend
    
to choose a database in that instance.
It is always best to test CLOUD SQL set up on the standalone app, before deploying.

3. Change port to 8080 from 9090

    server.port=8080

AppEngine is serving to the port 8080. If we leave other port like 9090 that we use in develeopment environment,
we will have 502 bad gateway error when we try to use our backend after deployment.

4. Comment all lines used to configure local mysql db in application.properties

    spring.datasource.driver-class=com.mysql.cj.jdbc.Driver
    spring.datasource.username=springuser
    spring.datasource.password=Test623@
    spring.datasource.url=jdbc:mysql://localhost:3306/db_example
    
In future we can move those options to application-mysql.properties.
    
5. To start deploying the project

    gcloud init

To set up the proper project on GCP

    mvn appengine:deploy

to deploy it.

