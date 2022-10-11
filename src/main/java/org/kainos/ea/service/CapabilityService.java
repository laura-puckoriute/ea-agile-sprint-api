package org.kainos.ea.service;

import org.kainos.ea.data.BandLevelData;
import org.kainos.ea.data.CapabilityData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.models.BandLevel;
import org.kainos.ea.models.Capability;
import org.kainos.ea.util.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class CapabilityService {

    private CapabilityData capabilityData;

    private DatabaseConnection databaseConnection;

    public CapabilityService( CapabilityData capabilityData, DatabaseConnection databaseConnection ) {

        this.capabilityData = capabilityData;
        this.databaseConnection = databaseConnection;
    }

    public List<Capability> getCapabilities() throws DatabaseConnectionException, SQLException, DataNotFoundException {

        List<Capability> capabilities = capabilityData.getCapabilities( databaseConnection.getConnection() );

        if ( !(capabilities.size() > 0) ) throw new DataNotFoundException();

        return capabilities;
    }
}
