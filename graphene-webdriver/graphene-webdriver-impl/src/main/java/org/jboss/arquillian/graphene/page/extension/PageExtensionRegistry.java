package org.jboss.arquillian.graphene.page.extension;

import java.util.Collection;

import org.jboss.arquillian.graphene.page.PageExtension;

/**
 * Registry of {@link PageExtension}s required by the current test.
 * 
 * @author Lukas Fryc
 */
public interface PageExtensionRegistry {

    /**
     * Requires given extensions to be injected to the page
     * 
     * @param extensions
     */
    void require(PageExtension... extensions);

    /**
     * Loads all
     * 
     * @return
     */
    Collection<PageExtension> getRequiredExtensions();

    /**
     * Opens registry for modifications (usually before test)
     */
    void open();

    /**
     * Closes registry for modifications (once test started)
     */
    void close();

    /**
     * Flushes the registered {@link PageExtension}s
     */
    void flush();

    /**
     * Returns true if method {@link #close()} has been called and no call to {@link #open()} has been done from that moment.
     */
    boolean isClosed();
}
