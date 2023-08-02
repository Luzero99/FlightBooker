import {
  Paper,
  Stack,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TablePagination,
  TableRow,
} from '@mui/material';
import React from 'react';
import { FlightsDto } from '../../types/FlightsDto';
import { DateTime } from 'luxon';

export const Main: React.FC<{ flights: FlightsDto[] }> = ({ flights }) => {
  const formattedFlights =
    flights.length > 0
      ? flights
          .sort(
            (a, b) =>
              new Date(b.departure).getTime() - new Date(a.departure).getTime()
          )
          .map((flight) => {
            const departureLocal = DateTime.fromISO(flight.departure).setZone(
              'local'
            );
            const arrivalLocal = DateTime.fromISO(flight.arrival).setZone(
              'local'
            );

            const dateFormat = 'HH:mm dd-MM-yyyy';
            const departureFormatted = departureLocal.toFormat(dateFormat);
            const arrivalFormatted = arrivalLocal.toFormat(dateFormat);

            return {
              ...flight,
              departure: departureFormatted,
              arrival: arrivalFormatted,
            };
          })
      : [];

  return (
    <main>
      <Stack sx={{ padding: '4rem' }}>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>From</TableCell>
                <TableCell>To</TableCell>
                <TableCell>Airline</TableCell>
                <TableCell>Departure</TableCell>
                <TableCell>Arrival</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {formattedFlights.map((row) => (
                <TableRow
                  key={Math.random()}
                  sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                >
                  <TableCell>{row.from}</TableCell>
                  <TableCell>{row.to}</TableCell>
                  <TableCell>{row.airline}</TableCell>
                  <TableCell>{row.departure}</TableCell>
                  <TableCell>{row.arrival}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Stack>
    </main>
  );
};
