import * as React from "react";
import { useForm, Controller } from "react-hook-form";
import axios from "axios";
import Avatar from "@mui/material/Avatar";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Link from "@mui/material/Link";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import LockOutlinedIcon from "@mui/icons-material/LockOutlined";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import Footer from "../Footer";
import InputForm from "../Components/InputForm";

const theme = createTheme();

export default function SignUp() {
  const { handleSubmit, reset, control } = useForm();
  const onSubmit = (data) => {
    console.log(data);
  };
  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: "secondary.main" }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          회원 가입
        </Typography>
        <Box
          component="form"
          noValidate
          onSubmit={handleSubmit(onSubmit)}
          sx={{ mt: 3 }}
        >
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <InputForm
                control={control}
                name="firstName"
                label="이름"
                validationRules={{ required: "이름을 입력해주세요" }}
                textFieldProps={{ required: true, fullWidth: true }}
              />
            </Grid>
            <Grid item xs={12}>
              <InputForm
                control={control}
                name="nickname"
                label="닉네임"
                validationRules={{
                  required: "닉네임을 입력해주세요",
                  validate: {
                    isDuplicate: async (data) => {
                      const result = await axios.get(
                        "/api/user/existsByNickName",
                        {
                          params: {
                            nickName: data,
                          },
                        }
                      );
                      return !result.data
                        ? !result.data
                        : "중복된 닉네임입니다";
                    },
                  },
                }}
                textFieldProps={{ required: true, fullWidth: true }}
              />
            </Grid>
            <Grid item xs={12}>
              <InputForm
                control={control}
                name="email"
                label="이메일"
                validationRules={{
                  required: "이메일을 입력해주세요",
                  validate: {
                    isDuplicate: async (data) => {
                      const result = await axios.get(
                        "/api/user/existsByMailAddress",
                        {
                          params: {
                            mailAddress: data,
                          },
                        }
                      );
                      return !result.data
                        ? !result.data
                        : "중복된 이메일입니다";
                    },
                  },
                }}
                textFieldProps={{ required: true, fullWidth: true }}
              />
            </Grid>
            <Grid item xs={12}>
              <InputForm
                control={control}
                name="password"
                label="비밀번호"
                validationRules={{
                  required: "비밀번호를 입력해주세요",
                  validate: {
                    isDuplicate: async (data) => {
                      const result = await axios.get(
                        "/api/user/existsByMailAddress",
                        {
                          mailAddress: data,
                        }
                      );
                      return result.data;
                    },
                  },
                }}
                textFieldProps={{ required: true, fullWidth: true }}
              />
            </Grid>
            <Grid item xs={12}>
              <InputForm
                control={control}
                name="address"
                label="주소"
                validationRules={{ required: "주소를 입력해주세요" }}
                textFieldProps={{ required: true, fullWidth: true }}
              />
            </Grid>
          </Grid>
          <Button
            type="submit"
            fullWidth
            variant="contained"
            sx={{ mt: 3, mb: 2 }}
          >
            회원가입
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="/signin" variant="body2">
                로그인 하기
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
      <Footer mt={2} />
    </Container>
  );
}
