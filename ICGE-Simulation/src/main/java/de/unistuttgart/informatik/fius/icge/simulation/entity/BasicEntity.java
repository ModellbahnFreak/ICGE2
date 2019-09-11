/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 *
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.icge.simulation.entity;

import java.lang.ref.WeakReference;

import de.unistuttgart.informatik.fius.icge.simulation.Playfield;
import de.unistuttgart.informatik.fius.icge.simulation.Position;
import de.unistuttgart.informatik.fius.icge.simulation.Simulation;
import de.unistuttgart.informatik.fius.icge.simulation.exception.EntityNotOnFieldException;
import de.unistuttgart.informatik.fius.icge.ui.BasicDrawable;
import de.unistuttgart.informatik.fius.icge.ui.Drawable;


/**
 * A basic implementation of {@link Entity}
 * 
 * @author Tim Neumann
 */
public abstract class BasicEntity implements Entity {
    
    private WeakReference<Playfield> field;
    
    /**
     * @throws EntityNotOnFieldException
     *     if this entity is not on a playfield
     */
    @Override
    public Position getPosition() {
        return getPlayfield().getEntityPosition(this);
    }
    
    /**
     * Get the texture handle, with which to get the texture for this entity.
     * 
     * @return the texture handle for the texture of this entity
     */
    protected abstract String getTextureHandle();
    
    /**
     * Get the z position of this entity.
     * <p>
     * The z position is used to order entities on the same field.
     * </p>
     * 
     * @return the z position of this entity.
     */
    protected abstract int getZPosition();
    
    /**
     * @throws EntityNotOnFieldException
     *     if this entity is not on a playfield
     */
    @Override
    public Drawable getDrawInformation() {
        Position pos = this.getPosition();
        return new BasicDrawable(pos.getX(), pos.getY(), getZPosition(), getTextureHandle());
    }
    
    @Override
    public void initOnPlayfield(Playfield playfield) {
        if (playfield == null) throw new IllegalArgumentException("The given playfield is null.");
        this.field = new WeakReference<>(playfield);
    }

    /**
     * Check whether this entity is on a playfield
     * 
     * @return true if and only if this entity is on a playfield
     */
    public boolean isOnPlayfield() {
        if (this.field == null) return false;
        Playfield playfield = this.field.get();
        if (playfield == null) {
            // Was on playfield, but no reference to it left, so it does not have a reference to this either.
            this.field = null;
            return false;
        }
        if (!playfield.containsEntity(this)) {
            // Was on playfield, but no more.
            this.field = null;
            return false;
        }
        return true;
    }
    
    /**
     * Get the playfield of this entity.
     * 
     * @return the playfield
     * @throws EntityNotOnFieldException
     *     if this entity is not on a playfield
     */
    protected Playfield getPlayfield() {
        if (!isOnPlayfield()) throw new EntityNotOnFieldException("This entity is not on a playfield");
        return this.field.get();
    }
    
    /**
     * Get the simulation of this entity.
     * 
     * @return the simulation
     * @throws EntityNotOnFieldException
     *     if this entity is not on a playfield
     * @throws IllegalStateException
     *     if the playfield of this entity is not part of any simulation
     */
    protected Simulation getSimulation() {
        return getPlayfield().getSimulation();
    }
    
}