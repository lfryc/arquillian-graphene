package org.jboss.arquillian.graphene.page;

/**
 * Denotes extension to be injected to the page
 * 
 * @author Lukas Fryc
 */
public interface PageExtension {

    /**
     * Detects if extension is already loaded to the page
     * 
     * @return the if extension is loaded in the page; false otherwise
     */
    boolean isInstalled();

    /**
     * Returns the name of the script that can uniquely identify this script
     */
    String getName();

    /**
     * Returns the source code of the script to be injected to the page
     */
    String getExtensionScript();
}
