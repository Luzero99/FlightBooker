import { Autocomplete, Box, Button, Stack, TextField } from '@mui/material';
import React, { FormEvent, useEffect, useState } from 'react';
import { AirportResponseDto } from '../../types/AirportResponseDto';
import { DEFAULT_FROM, DEFAULT_TO } from '../../utils/constants';

export const Header: React.FC<{
  fetchFlights: (from: string, to: string) => Promise<void>;
  airports: AirportResponseDto;
}> = ({ fetchFlights, airports }) => {
  const [from, setFrom] = useState<string | null>(null);
  const [to, setTo] = useState<string | null>(null);

  const clearForm = () => {
    setFrom(null);
    setTo(null);
  };

  const handleSubmitAction = async (event: FormEvent) => {
    event.preventDefault();

    if (!from || !to) {
      return;
    }

    await fetchFlights(from, to);

    clearForm();
  };

  return (
    <header>
      <Box sx={{ width: '100vw', padding: '1.8rem', backgroundColor: '#eee' }}>
        <form onSubmit={handleSubmitAction}>
          <Stack direction="row" justifyContent="center" gap="8rem">
            <Autocomplete
              sx={{ width: '16rem' }}
              id="from"
              value={from}
              onChange={(event, newValue) => setFrom(newValue)}
              renderInput={(params) => <TextField {...params} label="From" />}
              options={airports.map(({ city, iata }) => `${city} (${iata})`)}
            />
            <Autocomplete
              sx={{ width: '16rem' }}
              id="to"
              value={to}
              onChange={(event, newValue) => setTo(newValue)}
              renderInput={(params) => <TextField {...params} label="To" />}
              options={airports.map(({ city, iata }) => `${city} (${iata})`)}
            />
            <Button variant="contained" type="submit">
              Search
            </Button>
          </Stack>
        </form>
      </Box>
    </header>
  );
};
