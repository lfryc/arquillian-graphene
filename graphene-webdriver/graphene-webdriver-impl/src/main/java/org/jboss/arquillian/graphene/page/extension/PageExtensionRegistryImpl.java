package org.jboss.arquillian.graphene.page.extension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import org.jboss.arquillian.graphene.page.PageExtension;

/**
 * The implementation of {@link PageExtensionRegistry}
 * 
 * @author Lukas Fryc
 */
public class PageExtensionRegistryImpl implements PageExtensionRegistry {

    private Set<PageExtension> pageExtensions;

    private boolean closed = false;

    /*
     * 
     */
    @Override
    public void require(PageExtension... extensions) {
        if (isClosed()) {
            throw new IllegalStateException("Can't add required extensions when registry has been closed yet");
        }
        pageExtensions.addAll(Arrays.asList(extensions));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.graphene.page.extension.PageExtensionRegistry#getRequiredExtensions()
     */
    @Override
    public Collection<PageExtension> getRequiredExtensions() {
        if (!isClosed()) {
            throw new IllegalStateException("Can't provide list of required extensions when registry has not been closed");
        }
        return Collections.unmodifiableSet(pageExtensions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.graphene.page.extension.PageExtensionRegistry#open()
     */
    @Override
    public void open() {
        closed = false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.graphene.page.extension.PageExtensionRegistry#close()
     */
    @Override
    public void close() {
        closed = true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.graphene.page.extension.PageExtensionRegistry#flush()
     */
    @Override
    public void flush() {
        pageExtensions.clear();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.jboss.arquillian.graphene.page.extension.PageExtensionRegistry#isClosed()
     */
    @Override
    public boolean isClosed() {
        return closed;
    }
}
