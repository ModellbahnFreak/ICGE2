/*
 * This source file is part of the FIUS ICGE project.
 * For more information see github.com/FIUS/ICGE2
 * 
 * Copyright (c) 2019 the ICGE project authors.
 * 
 * This software is available under the MIT license.
 * SPDX-License-Identifier:    MIT
 */
package de.unistuttgart.informatik.fius.icge.ui;

import java.util.List;
import java.util.Set;


/**
 * The SimulationProxy interface
 *
 * @author Tobias Wältken
 * @version 1.0
 */
public interface SimulationProxy {
    
    //
    // Toolbar - Clock
    //
    
    /**
     * The clock button state represents the states of the clock buttons in the ui
     */
    public enum ClockButtonState {
        /** Indicates a running simulation clock */
        PLAYING,
        /** Indicates a stoped simulation clock but a unclean simulation */
        PAUSED,
        /** Indicates a clean simulation */
        STOPPED,
        /** Indicates an error or unavailable clock or simulation */
        BLOCKED
    }
    
    /**
     * The control button state represents the status of the control buttons in the ui
     */
    public enum ControlButtonState {
        /** Indicates the user input is in view mode */
        VIEW,
        /** Indicates the user input is in add mode */
        ADD,
        /** Indicates the user input is in sub mode */
        SUB,
        /** Indicates that the user input is blocked or unavailable */
        BLOCKED
    }
    
    /**
     * The button state listener allows the ui to react to change requests.
     */
    public interface ButtonStateListener {
        
        /**
         * This function changes the enabled state of the clock buttons
         *
         * @param state
         *     the state of the buttons
         */
        void changeButtonState(ClockButtonState state);
        
        /**
         * This function changes the enabled state of the control buttons
         *
         * @param state
         *     the state of the buttons
         */
        void changeButtonState(ControlButtonState state);
    }
    
    /**
     * This function sets the one button state listener and should only be called by the ui itself. The only way to
     * reset this listener is to explicitly set it to null thus removing the old listener.
     *
     * @param listener
     *     The listener to use.
     */
    void setButtonStateListener(ButtonStateListener listener);
    
    /**
     * This is to identify the buttons
     */
    public enum ButtonType {
        /** The play button in the toolbar */
        PLAY,
        /** The step button in the toolbar */
        STEP,
        /** The pause button in the toolbar */
        PAUSE,
        /** The stop button in the toolbar */
        STOP,
        /** The view button in the toolbar */
        VIEW,
        /** The add button in the toolbar */
        ADD,
        /** The sub button in the toolbar */
        SUB
    }
    
    /**
     * This is called when a button is pressed by the user
     *
     * @param type
     *     The type of the pressed button
     */
    void buttonPressed(ButtonType type);
    
    /**
     * This speed slider listener allows the speed slider to react to requests
     */
    public interface SpeedSliderListener {
        /**
         * Getter function for the currently selected speed
         * 
         * @return Returns the slider position a integer between 0 and 10 (both inclusive)
         */
        int getSpeed();
        
        /**
         * Setter for the position of the slider
         * 
         * @param speed
         *     The new position for the slider a integer between 0 and 10 (both inclusive)
         */
        void setSpeed(int speed);
    }
    
    /**
     * This function sets the one speed slider listener and should only be called by the ui itself. The only way to
     * reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     The listener to use
     */
    void setSpeedSliderListener(SpeedSliderListener listener);
    
    /**
     * This is called if the speed slider is changed by the user
     *
     * @param value
     *     The new selected speed
     */
    void simulationSpeedChange(int value);
    
    //
    // Toolbar - Task
    //
    
    /**
     * The TaskSelectorListener allows the simulation to talk to the task selector
     */
    public interface TaskSelectorListener {
        
        /**
         * Getter function for the currently selected element
         * 
         * @return returns the selected element
         */
        String getSelectedElement();
        
        /**
         * Setter function for the currently active element
         * 
         * @param element
         *     The element to select
         */
        void setSelectedElement(String element);
        
        /**
         * Setter function for all available elements. Use null to clear.
         * 
         * @param elements
         *     The set of new selectable values
         */
        void setElements(Set<String> elements);
        
        /**
         * This function enables the task selector
         */
        void enable();
        
        /**
         * This function disables the task selector and clears all elements!
         */
        void disable();
    }
    
    /**
     * This function is used to set the one task selector listener and should only be called by the ui itself. The only
     * way to reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     the listener to store
     */
    void setTaskSelectorListener(TaskSelectorListener listener);
    
    /**
     * This function gets called if the selected task changes
     * 
     * @param element
     *     contains the newly selected element
     */
    void selectedTaskChange(String element);
    
    //
    // Toolbar - entity select
    //
    
    /**
     * The entity selector listener
     */
    public interface EntitySelectorListener {
        
        /**
         * Getter for the current entity
         * 
         * @return The entity name
         */
        String getCurrentEntity();
        
        /**
         * set the current entity. Caution this is unchecked and selected even if it does not exist.
         * 
         * @param entity
         *     The entity to select
         */
        void setCurrentEntity(String entity);
        
        /**
         * Add a element to the drop down menu
         * 
         * @param name
         *     The name of the entity to add
         * @param textureId
         *     The texture id of the entity
         */
        void addElement(String name, String textureId);
        
        /**
         * This function enables the entity selector
         */
        void enable();
        
        /**
         * This function disables the entity selector and clears all elements!
         */
        void disable();
    }
    
    /**
     * This function is used to set the one entity selector listener and should only be called by the ui itself. The
     * only way to reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     the listener to store
     */
    void setEntitySelectorListener(EntitySelectorListener listener);
    
    /**
     * This gets called when the user changes the selected element
     * 
     * @param name
     *     The name of the selected element
     */
    void selectedEntityChanged(String name);
    
    //
    // Entity Drawing
    //
    
    /**
     * The entity draw listener allows the simulation to trigger redraws and set the drawable list.
     */
    public interface EntityDrawListener {
        
        /**
         * Set the current list of Drawables to be rendered.
         *
         * @param drawables
         *     the list of Drawables to render
         */
        void setDrawables(List<Drawable> drawables);
        
        /**
         * (Re-)Draws the playfield.
         * 
         * @param tickCount
         *     The number of the current tick
         */
        void draw(long tickCount);
    }
    
    /**
     * This function is used to set the one entity draw listener and should only be called by the ui itself. The only
     * way to reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     the listener to store
     */
    void setEntityDrawListener(EntityDrawListener listener);
    
    //
    // Entity placing
    //
    
    /**
     * The tool state listener allows the playfield to get informed about the currently active tool adn selected entity.
     */
    public interface ToolStateListener {
        
        /**
         * Set the currently active tool.
         *
         * @param selectedTool
         *     the currently selected tool
         */
        void setSelectedTool(ControlButtonState selectedTool);
        
        /**
         * Set the selected entity type.
         * 
         * @param typeName
         *     The selected type name
         * @param textureHandle
         *     The texture handle for the selected type name
         */
        void setSelectedEntityType(String typeName, String textureHandle);
    }
    
    /**
     * This function is used to set the one tool state listener and should only be called by the ui itself. The only way
     * to reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     the listener to store
     */
    void setToolStateListener(ToolStateListener listener);
    
    /**
     * Get a set of registered programs that can be used with an entity of the given type.
     * 
     * @param typeName
     *     the name of the entity type
     * @return the set of program identifiers
     */
    Set<String> getAvailableProgramsForEntityType(String typeName);
    
    /**
     * Spawn a new entity of the given type at the given position and bind the program to this entity.
     * 
     * @param typeName
     *     the entity type to instantiate
     * @param x
     *     coordinate
     * @param y
     *     coordinate
     * @param program
     *     program name; use {@code null} to not set a program
     */
    void spawnEntityAt(String typeName, int x, int y, String program);
    
    /**
     * Clear all entities in the given cell.
     * 
     * @param x
     *     coordinate
     * @param y
     *     coordinate
     */
    void clearCell(int x, int y);
    
    //
    // Sidebar - Simulation Tree
    //
    
    /**
     * The listener class for the simulation tree
     */
    public interface SimulationTreeListener {
        
        /**
         * Getter function for the currently selected element
         * 
         * @return Returns the selected element as {@link SimulationTreeNode}
         */
        SimulationTreeNode getSelectedElement();
        
        /**
         * This function allows you to set the selected node
         * 
         * @param node
         *     The node to select
         */
        void setSelectedElement(SimulationTreeNode node);
        
        /**
         * This function allows a complete exchange of the simulation tree. Calls updateSimulationTree implicitly.
         * 
         * @param rootNode
         *     The new root node of the simulation
         */
        void setRootNode(SimulationTreeNode rootNode);
        
        /**
         * This function rebuilds the simulation tree and thus updating it if nodes below the rootnode changed where
         * removed or added.
         */
        void updateSimulationTree();
        
        /**
         * Enables the simulation tree
         */
        void enable();
        
        /**
         * Disables the simulation tree
         */
        void disable();
    }
    
    /**
     * This function is used to set the one simulation tree listener and should only be called by the ui itself. The
     * only way to reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     the listener to store
     */
    void setSimulationTreeListener(SimulationTreeListener listener);
    
    /**
     * This function gets called when the user selects a different node
     * 
     * @param node
     *     The node with was selected
     */
    void selectedSimulationEntityChange(SimulationTreeNode node);
    
    /**
     * This interface is used by the entity explorer
     */
    public interface EntityInspectorListener {
        
        /**
         * Getter for the current entity name
         * 
         * @return returns the name
         */
        String getName();
        
        /**
         * Setter for the current entity name
         * 
         * @param name
         */
        void setName(String name);
        
        /**
         * This resets the entity entries, it deletes everything that is there an recreates it
         * 
         * @param entries
         */
        void setEntityEntries(EntityInspectorEntry[] entries);
        
        /**
         * enables the entity editor
         */
        void enable();
        
        /**
         * disables the entity editor
         */
        void disable();
    }
    
    /**
     * This function is used to set the one entity insoecter listener and should only be called by the ui itself. The
     * only way to reset this listener is to explicitly set it to null thus removing the old listener.
     * 
     * @param listener
     *     the listener to store
     */
    void setEntityInspectorListener(EntityInspectorListener listener);
    
    /**
     * This function gets called when a user changes a value or fires a function in the ui
     * 
     * @param name
     *     The name of the setting
     * @param value
     *     The new user selected value
     */
    void entityValueChange(String name, String value);
}
