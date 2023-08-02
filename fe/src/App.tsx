import React, { useEffect, useState } from 'react';
import { Header } from './components/Header/Header';
import { Main } from './components/Main/Main';
import { FlightsDto } from './types/FlightsDto';
import { Box, CircularProgress } from '@mui/material';
import { AirportResponseDto } from './types/AirportResponseDto';
import { DEFAULT_FROM, DEFAULT_TO } from './utils/constants';

function App() {
  const [flights, setFlights] = useState<FlightsDto[]>([]);
  const [airports, setAirports] = useState<AirportResponseDto>([]);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    (async () => {
      await fetchAirports();
      await fetchFlights(DEFAULT_FROM, DEFAULT_TO);
    })();
  }, []);

  const fetchFlights = async (from: String, to: String) => {
    try {
      setIsLoading(true);
      const fromIata = from.substring(from.indexOf('(') + 1, from.indexOf(')'));
      const toIata = to.substring(to.indexOf('(') + 1, to.indexOf(')'));
      const url = `http://localhost:8080/flight?fromIata=${fromIata}&toIata=${toIata}`;
      const response = await fetch(url, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' },
      });
      const data = await response.json();
      setFlights(data);
      setIsLoading(false);
    } catch (error) {
      setIsLoading(false);
      console.error('Error fetching flights:', error);
    }
  };

  const fetchAirports = async () => {
    try {
      const response = await fetch('http://localhost:8080/airport', {
        method: 'GET',
      });
      const data = await response.json();
      setAirports(data);
    } catch (error) {
      setIsLoading(false);
      console.error('Error fetching airports:', error);
    }
  };

  return (
    <div className="App">
      {isLoading ? (
        <Box sx={{ textAlign: 'center', mt: '2.8rem' }}>
          <CircularProgress size="16rem" />
        </Box>
      ) : (
        <React.Fragment>
          <Header fetchFlights={fetchFlights} airports={airports} />
          <Main flights={flights} />
        </React.Fragment>
      )}
    </div>
  );
}

export default App;
