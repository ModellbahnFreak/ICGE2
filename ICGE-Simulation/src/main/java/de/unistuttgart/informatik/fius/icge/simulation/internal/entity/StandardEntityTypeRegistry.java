/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 * 
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.icge.simulation.internal.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import de.unistuttgart.informatik.fius.icge.log.Logger;
import de.unistuttgart.informatik.fius.icge.simulation.entity.Entity;
import de.unistuttgart.informatik.fius.icge.simulation.entity.EntityTypeRegistry;
import de.unistuttgart.informatik.fius.icge.simulation.exception.ElementExistsException;
import de.unistuttgart.informatik.fius.icge.ui.exception.ListenerSetException;
import de.unistuttgart.informatik.fius.icge.ui.SimulationProxy.EntitySelectorListener;


/**
 * Standard EntityTypeRegistry implementation.
 * 
 * @author Fabian Bühler
 */
public class StandardEntityTypeRegistry implements EntityTypeRegistry {
    
    private final Map<String, Supplier<? extends Entity>> typeToEntityFactory = new HashMap<>();
    private final Map<String, String>                     typeToTextureHandle = new HashMap<>();
    
    private EntitySelectorListener entitySelectorListener;
    
    @Override
    public void registerEntityType(String typeName, String textureHandle, Class<? extends Entity> entityType) {
        if (entityType == null) throw new IllegalArgumentException("Entity type class object cannot be null!");
        final Supplier<? extends Entity> entityFactory = () -> {
            try {
                return entityType.getDeclaredConstructor().newInstance();
            } catch (
                    NoSuchMethodException | InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e
            ) {
                e.printStackTrace(Logger.error);
                // could not instantiate a new entity
                return null;
            }
        };
        this.registerEntityType(typeName, textureHandle, entityFactory);
    }
    
    @Override
    public synchronized void registerEntityType(String typeName, String textureHandle, Supplier<? extends Entity> entityFactory) {
        if (typeName == null || typeName.equals("")) throw new IllegalArgumentException("Type name cannot be null or empty!");
        if (
            textureHandle == null || textureHandle.equals("")
        ) throw new IllegalArgumentException("Texture handle cannot be null or empty!");
        if (entityFactory == null) throw new IllegalArgumentException("Entity factory cannot be null!");
        if (entityFactory.get() == null) throw new IllegalArgumentException("Unable to instantiate a new entity!");
        if (this.typeToEntityFactory.containsKey(typeName)) throw new ElementExistsException();
        this.typeToEntityFactory.put(typeName, entityFactory);
        this.typeToTextureHandle.put(typeName, textureHandle);
        
        if (this.entitySelectorListener != null) {
            this.entitySelectorListener.addElement(typeName, textureHandle);
        }
    }
    
    @Override
    public Set<String> getRegisteredEntityTypes() {
        return this.typeToEntityFactory.keySet();
    }
    
    @Override
    public String getTextureHandleOfEntityType(String typeName) {
        if (typeName == null || typeName.equals("")) throw new IllegalArgumentException("Type name cannot be null or empty!");
        return this.typeToTextureHandle.get(typeName);
    }
    
    @Override
    public Entity getNewEntity(String typeName) {
        if (typeName == null || typeName.equals("")) throw new IllegalArgumentException("Type name cannot be null or empty!");
        Supplier<? extends Entity> entityFactory = this.typeToEntityFactory.get(typeName);
        if (entityFactory == null) return null;
        return entityFactory.get();
    }
    
    /**
     * Add entity selector listener that gets informed about all entity types added.
     * 
     * @param listener
     *     the listener to set; use null to remove listener
     * @throws ListenerSetException
     *     if the listener is already set and the provided listener is not {@code null}.
     */
    public synchronized void setEntitySelectorListener(EntitySelectorListener listener) {
        if ((this.entitySelectorListener == null) || (listener == null)) {
            if (listener != null) {
                this.typeToEntityFactory.keySet().stream().forEachOrdered((typeName) -> {
                    listener.addElement(typeName, this.typeToTextureHandle.get(typeName));
                });
            }
            this.entitySelectorListener = listener;
        } else throw new ListenerSetException();
    }
}
