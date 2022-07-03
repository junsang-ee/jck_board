import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { Grid, Typography } from "@mui/material";
import moment from "moment";

function createData(name, calories, fat, carbs, protein) {
  return { name, calories, fat, carbs };
}

const rows = [
  createData("Frozen yoghurt", 159, "2022-06-21", 24, 4.0),
  createData("Ice cream sandwich", 237, "2022-06-21", 37, 4.3),
  createData("Eclair", 262, "2022-06-21", 6.0),
  createData("Cupcake", 305, "2022-06-21", 4.3),
  createData("Gingerbread", 356, "2022-06-21", 49, 3.9),
];

export default function BasicTable() {
  return (
    <Grid item xs={12} md={12} lg={12}>
      <Grid container alignItems="center" justifyContent="space-between">
        <Grid item>
          <Typography variant="h5" paragraph>
            커뮤니티
          </Typography>
        </Grid>
      </Grid>
      <Grid item>
        <TableContainer component={Paper}>
          <Table sx={{ minWidth: 650 }} aria-label="simple table">
            <TableHead>
              <TableRow>
                <TableCell>제목</TableCell>
                <TableCell align="right">글쓴이</TableCell>
                <TableCell align="right">작성 시간</TableCell>
                <TableCell align="right">조회수</TableCell>
              </TableRow>
            </TableHead>
            <TableBody>
              {rows.map((row) => (
                <TableRow
                  key={row.name}
                  sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                >
                  <TableCell component="th" scope="row">
                    {row.name}
                  </TableCell>
                  <TableCell align="right">{row.calories}</TableCell>
                  <TableCell align="right">{row.fat}</TableCell>
                  <TableCell align="right">{row.carbs}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </TableContainer>
      </Grid>
    </Grid>
  );
}
