package com.seaclub.Manager;

import com.seaclub.Model.Boat;
import com.seaclub.Model.StorageRegister;
import com.seaclub.server.DB;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements a model for managing list of storage register
 */
public class StorageRegisterManager {
    private List<StorageRegister> registers;
    private StorageRegister lastStorageRegister;

    private static StorageRegisterManager instance = null;

    /**
     * The constructor is private so it is accessible only within the class.
     **/
    private StorageRegisterManager(){
        registers = new ArrayList<StorageRegister>();
    }

    /**
     * The constructor is called only if the static instance is null, so only the first time
     * that the getInstance() method is invoked.
     * All the other times the same instance object is returned.
     * @return the instance object is returned.
     **/
    public static StorageRegisterManager getInstance() {
        if (instance == null)
            instance = new StorageRegisterManager();
        return instance;
    }

    /**
     * @param lastStorageRegister set the last storage register
     */
    public void setLastStorageRegister(StorageRegister lastStorageRegister) {
        this.lastStorageRegister = lastStorageRegister;
    }


    /**
     * @return the list of storage register
     */
    public List<StorageRegister> getRegisters() {
        return registers;
    }


    /**
     * @param registers set the list of storage register
     */
    public void setRegisters(List<StorageRegister> registers) {
        this.registers = registers;
    }


    /**
     * Method used for updating information about all storage register in the list
     */
    public void updateList(){
        DB.getInstance().getBoatStorageQuote();
    }


    /**
     * Method used for getting the last payment made for storage quote about a specific boat
     * @param boat the specific boat
     * @return last storage register about the boat
     */
    public StorageRegister getLastStorageRegisterBoat(Boat boat){
        try {
            updateList();
            DB.getInstance().getLastBoatStorageQuote(boat);
            return lastStorageRegister;
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

    /**
     * Method used for adding new storage register
     * @param storageRegister the new storage register
     */
    public boolean addNewStorageRegister(StorageRegister storageRegister){
        try{
            if(DB.getInstance().addNewStorageRegister(storageRegister)){
                updateList();
                return true;
            }else
                return false;
        }catch (Exception e){
            System.out.println(e.toString());
            return false;
        }
    }
}