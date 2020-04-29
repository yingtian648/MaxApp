/*     */ package com.using.adlibrary.utils;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ShellUtils
/*     */ {
/*     */   public static final String COMMAND_SU = "su root";
/*     */   public static final String COMMAND_SH = "sh";
/*     */   public static final String COMMAND_EXIT = "exit\n";
/*     */   public static final String COMMAND_LINE_END = "\n";
/*     */   
/*     */   private ShellUtils() {
/*  35 */     throw new AssertionError();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean checkRootPermission() {
/*  44 */     return ((execCommand("echo root", true, false)).result == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandResult execCommand(String command, boolean isRoot) {
/*  56 */     return execCommand(new String[] { command }, isRoot, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandResult execCommand(List<String> commands, boolean isRoot) {
/*  68 */     return execCommand((commands == null) ? null : commands.<String>toArray(new String[0]), isRoot, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandResult execCommand(String[] commands, boolean isRoot) {
/*  80 */     return execCommand(commands, isRoot, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandResult execCommand(String command, boolean isRoot, boolean isNeedResultMsg) {
/*  93 */     return execCommand(new String[] { command }, isRoot, isNeedResultMsg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandResult execCommand(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
/* 106 */     return execCommand((commands == null) ? null : commands.<String>toArray(new String[0]), isRoot, isNeedResultMsg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static CommandResult execCommand(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
/* 122 */     int result = -1;
/* 123 */     if (commands == null || commands.length == 0) {
/* 124 */       return new CommandResult(result, null, null);
/*     */     }
/*     */     
/* 127 */     Process process = null;
/* 128 */     BufferedReader successResult = null;
/* 129 */     BufferedReader errorResult = null;
/* 130 */     StringBuilder successMsg = null;
/* 131 */     StringBuilder errorMsg = null;
/*     */     
/* 133 */     DataOutputStream os = null;
/*     */     try {
/* 135 */       process = Runtime.getRuntime().exec(isRoot ? "su root" : "sh");
/* 136 */       os = new DataOutputStream(process.getOutputStream());
/* 137 */       for (String command : commands) {
/* 138 */         if (command != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 143 */           os.write(command.getBytes());
/* 144 */           os.writeBytes("\n");
/* 145 */           os.flush();
/*     */         } 
/* 147 */       }  os.writeBytes("exit\n");
/* 148 */       os.flush();
/*     */       
/* 150 */       result = process.waitFor();
/*     */       
/* 152 */       if (isNeedResultMsg) {
/* 153 */         successMsg = new StringBuilder();
/* 154 */         errorMsg = new StringBuilder();
/* 155 */         successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
/* 156 */         errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
/*     */         String s;
/* 158 */         while ((s = successResult.readLine()) != null) {
/* 159 */           successMsg.append(s);
/*     */         }
/* 161 */         while ((s = errorResult.readLine()) != null) {
/* 162 */           errorMsg.append(s);
/*     */         }
/*     */       } 
/* 165 */     } catch (IOException e) {
/* 166 */       e.printStackTrace();
/* 167 */     } catch (Exception e) {
/* 168 */       e.printStackTrace();
/*     */     } finally {
/*     */       try {
/* 171 */         if (os != null) {
/* 172 */           os.close();
/*     */         }
/* 174 */         if (successResult != null) {
/* 175 */           successResult.close();
/*     */         }
/* 177 */         if (errorResult != null) {
/* 178 */           errorResult.close();
/*     */         }
/* 180 */       } catch (IOException e) {
/* 181 */         e.printStackTrace();
/*     */       } 
/*     */       
/* 184 */       if (process != null) {
/* 185 */         process.destroy();
/*     */       }
/*     */     } 
/* 188 */     return new CommandResult(result, (successMsg == null) ? null : successMsg.toString(), (errorMsg == null) ? null : errorMsg
/* 189 */         .toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class CommandResult
/*     */   {
/*     */     public int result;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String successMsg;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String errorMsg;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CommandResult(int result) {
/* 219 */       this.result = result;
/*     */     }
/*     */     
/*     */     public CommandResult(int result, String successMsg, String errorMsg) {
/* 223 */       this.result = result;
/* 224 */       this.successMsg = successMsg;
/* 225 */       this.errorMsg = errorMsg;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\androidstudio\workspace\TestProjects\reverseAPK\adlibrary-release\classes.jar!\co\\using\adlibrar\\utils\ShellUtils.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */