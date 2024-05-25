# The Steps to Run the LLM service Locally

* First download gguf from https://huggingface.co/TheBloke/Llama-2-13B-chat-GGUF , `llama-2-13b-chat.Q5_K_S.gguf`
* move this model to `model/llama-2-13b-chat.Q5_K_S.gguf`


## Running the frontend:
1. Navigate to the frontend directory: `cd frontend`
2. Install node `node:18.17.1`
3. Install PNPM: `npm install -g pnpm@9.1.2` `pnpm:9.1.2`
4. Install Dependencies: `pnpm install`
5. start the application in dev mode: `pnpm dev`
6. production build: `pnpm build`
7. any linter problem `pnpm lint:fix` or to check `pnpm lint`


## Running the backend
* run docker containers
* after making sure that the database and the frontend are running, run the `LlmServiceApplication`
* to run the Backend linter `mvn spotless:check` to fix `mvn spotless:check`
* Backend uses cpp plugin to run the modal , `C++11` `g++` `cmake`


## How to use Docker
* Run `docker compose up -d` 
* if something is changed in the front end and you want the latest changes `docker compose up -d --no-deps --build build-fe`
