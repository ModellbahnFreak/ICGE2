/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 * 
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.icge.simulation.internal.entity.program;

import java.util.concurrent.CompletableFuture;

import de.unistuttgart.informatik.fius.icge.simulation.entity.program.EntityProgram;
import de.unistuttgart.informatik.fius.icge.simulation.entity.program.EntityProgramState;
import de.unistuttgart.informatik.fius.icge.simulation.entity.program.RunningProgramInfo;


/**
 * An object holding information about the execution of a entity program.
 * 
 * @author Tim Neumann
 */
public class EntityProgramRunningInfo implements RunningProgramInfo {
    
    private EntityProgramState      state;
    private final EntityProgram     program;
    private CompletableFuture<Void> future;
    
    /**
     * Initialize
     * 
     * @param program
     *     the program for this object; must not be null
     * @throws IllegalArgumentException
     *     if the argument is null
     */
    public EntityProgramRunningInfo(final EntityProgram program) {
        if (program == null) throw new IllegalArgumentException("Argument is null.");
        
        this.program = program;
        this.state = EntityProgramState.NEW;
    }
    
    @Override
    public EntityProgramState getState() {
        return this.state;
    }
    
    /**
     * Set the state of this object
     * 
     * @param state
     *     the new state; must not be null
     * @throws IllegalArgumentException
     *     if the argument is null
     */
    public void setState(final EntityProgramState state) {
        if (state == null) throw new IllegalArgumentException("Argument is null.");
        this.state = state;
    }
    
    /**
     * Get the program of this object
     * 
     * @return program the program of this object; cannot be null
     */
    public EntityProgram getProgram() {
        return this.program;
    }
    
    @Override
    public CompletableFuture<Void> getFuture() {
        return this.future;
    }
    
    /**
     * @param future
     *     the future to set; may be null
     */
    public void setFuture(CompletableFuture<Void> future) {
        this.future = future;
    }
    
}
