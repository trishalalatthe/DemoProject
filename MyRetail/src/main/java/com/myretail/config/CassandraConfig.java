package com.myretail.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.convert.CassandraConverter;
import org.springframework.data.cassandra.core.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class CassandraConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(CassandraConfig.class);

	 /**
     * Constant String for Keyspace
     */
    private static final String KEYSPACE = "cassandra.keyspace";
    /**
     * Constant String for ContactPoints
     */
    private static final String CONTACTPOINTS = "cassandra.contactpoints";
    /**
     * Constant String for Port 
     */
    private static final String PORT = "cassandra.port";
    
    /**
     * Get the environment object
     */
    @Autowired
    private Environment environment;

    /**
     * Constructor
     */
    public CassandraConfig() {
    	logger.debug("Config()");
    }
    
    /**
     * Get the Cassandra DB KeySpace
     * @return cassandra db keyspace
     */
    private String getKeyspaceName() {
        return environment.getProperty(KEYSPACE);       
    }
    
    /**
     * Getter for the contact point
     * @return contact point
     */
    private String getContactPoints() {
        return environment
                .getProperty(CONTACTPOINTS);        
    }
    
    /**
     * Get the Port Number 
     * @return port number for the Cassandra DB
     */
    private int getPortNumber() {
        return Integer.parseInt(environment
                .getProperty(PORT));        
    }

    /**
     * @return CassandraClusterFactoryBean for the Cassandra Cluster
     */
    @Bean
    public CassandraClusterFactoryBean cluster() {
        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(getContactPoints());
        cluster.setPort(getPortNumber());
        return cluster;
    }

    /**
     * @return the mapping context.
     */
    @Bean
    public CassandraMappingContext mappingContext() {
        return new CassandraMappingContext();
    }

    /**
     * @return Cassandra converter
     */
    @Bean
    public CassandraConverter converter() {
        return new MappingCassandraConverter(mappingContext());
    }

    /**
     * @return the session for the Session Factory 
     * @throws Exception
     */
    @Bean
    public CassandraSessionFactoryBean session() throws Exception {
        CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
        cassandraSessionFactoryBean.setCluster(cluster().getObject());
        cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
        cassandraSessionFactoryBean.setConverter(converter());
        cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
        return cassandraSessionFactoryBean;
    }

    /**
     * @return the cassandra operations.
     * @throws Exception
     */
    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }
}

