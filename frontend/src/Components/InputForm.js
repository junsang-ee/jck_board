import React from "react";
import { Controller } from "react-hook-form";
import { TextField } from "@mui/material";

const InputForm = (props) => {
  const { validationRules, control, name, label, textFieldProps } = props;
  return (
    <Controller
      name={name}
      defaultValue=""
      control={control}
      rules={validationRules}
      render={({ field: { onChange, value }, fieldState: { error } }) => (
        <TextField
          {...textFieldProps}
          autoComplete="given-name"
          name={name}
          value={value}
          onChange={onChange}
          error={!!error}
          helperText={error ? error.message : null}
          label={label}
          autoFocus
        />
      )}
    />
  );
};

export default InputForm;
