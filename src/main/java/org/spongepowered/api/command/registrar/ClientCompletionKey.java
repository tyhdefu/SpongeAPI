package org.spongepowered.api.command.registrar;

import org.spongepowered.api.CatalogKey;
import org.spongepowered.api.util.annotation.CatalogedBy;

/**
 * Represents the client-side behaviour of a command parameter.
 */
@CatalogedBy(ClientCompletionKeys.class)
public interface ClientCompletionKey extends CatalogKey {

}
