# Use the official Node.js 18 image as base
FROM node:18

WORKDIR /app

# Set environment variables
ENV PNPM_VERSION 8.15.0

# Optionally, you can copy your application code here
COPY . /app

RUN rm -rf node_modules

# Install PNPM globally
RUN npm install -g pnpm@${PNPM_VERSION}

# Confirm installation
RUN pnpm --version

RUN pnpm install

EXPOSE 4040

CMD ["pnpm", "dev"]
