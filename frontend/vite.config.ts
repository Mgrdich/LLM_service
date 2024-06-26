import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react';
import path from 'path';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  resolve:{
    alias: {
      'ui': path.resolve(__dirname, './src/ui'),
      'hooks': path.resolve(__dirname, './src/hooks'),
      'pages': path.resolve(__dirname, './src/pages'),
      'utils': path.resolve(__dirname, './src/utils'),
      'models': path.resolve(__dirname, './src/models'),
      'context': path.resolve(__dirname, './src/context'),
    }
  },
  server: {
    port: 4040,
    host: true,
    watch: {
      usePolling: true
    }
  },
  preview: {
    port: 4040,
    strictPort: true,
  },
})
