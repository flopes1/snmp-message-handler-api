package com.poli.snmp.config.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesLoader
{
    private static final Logger log = LoggerFactory.getLogger(PropertiesLoader.class);
    private Properties properties;
    private static PropertiesLoader INSTANCE;

    private PropertiesLoader()
    {
        this.loadProperties();
    }

    private Properties loadProperties()
    {
        try
        {
            InputStream globalPropertiesStream = this.getClass().getClassLoader()
                    .getResourceAsStream(PropertiesLoaderConstants.PROPERTIES_FILE_NAME);
            this.properties = new Properties();
            this.properties.load(globalPropertiesStream);

            return this.properties;
        }
        catch (IOException e)
        {
            throw new RuntimeException("Failed to load autocomplete-data-retrieval.properties file", e);
        }
    }

    public static PropertiesLoader getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new PropertiesLoader();
        }

        return INSTANCE;
    }

    public String getProperty(String propertyName)
    {
        return properties.getProperty(propertyName);
    }

    public <T> T getProperty(String key, T defaultValue, Class<T> propertyClass)
    {
        return getProperty(this.loadProperties(), key, defaultValue, propertyClass);
    }

    @SuppressWarnings("unchecked")
    public <T> T getProperty(Properties globalProperties, String key, T defaultValue, Class<T> propertyClass)
    {
        try
        {
            T returnValue;
            String value = this.properties.getProperty(key);
            if (value == null || value.trim().length() == 0)
            {
                returnValue = defaultValue;
            }
            else if (propertyClass.equals(String.class))
            {
                returnValue = (T) value;
            }
            else if (propertyClass.equals(Integer.class) || propertyClass.equals(int.class))
            {
                returnValue = (T) new Integer(value);
            }
            else if (propertyClass.equals(Boolean.class) || propertyClass.equals(boolean.class))
            {
                returnValue = (T) new Boolean(value);
            }
            else if (propertyClass.equals(Float.class))
            {
                returnValue = (T) new Float(value);
            }
            else if (propertyClass.equals(Double.class) || propertyClass.equals(double.class))
            {
                returnValue = (T) new Double(value);
            }
            else if (propertyClass.equals(Long.class) || propertyClass.equals(long.class))
            {
                returnValue = (T) new Long(value);
            }
            else
            {
                log.warn("Key {} not found. Using default value {}.", key, defaultValue);

                returnValue = defaultValue;
            }
            return returnValue;
            /*
             * return (T) value; threw exception [java.lang.ClassCastException: java.lang.String cannot be cast to
             * java.lang.Boolean]
             */
        }
        catch (RuntimeException e)
        {
            log.error("Unexpected exception: " + e.getMessage(), e);
            return defaultValue;
        }
    }

}
