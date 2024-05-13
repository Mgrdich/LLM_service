# Use the official Node.js 18 image as base
FROM node:18 as builder

WORKDIR /app

COPY package.json .

# Set environment variables
ENV PNPM_VERSION 8.15.0

# Install PNPM globally
RUN npm install -g pnpm@${PNPM_VERSION}

# Confirm installation
RUN pnpm --version

RUN pnpm install

COPY . .

RUN pnpm build

FROM nginx
COPY nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=builder /app/dist /usr/share/nginx/html
CMD ["nginx", "-g", "daemon off;"]
